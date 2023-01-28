项目说明

Rbac0 - 最基本的实现
Rbac0WithResource - Rbac0 对复杂资源的处理实现
Rbac0WithGroup - Rbac0 带分组的实现，这里实现了用户分组
Rbac0WithHierarchy - Rbac0 角色继承概念的实现
Rbac0WithGroupHierarchy - 用户组的继承树实现，这里也实现了 **数据权限（datascope）**





用户登录时，在 AuthInterceptor 登录并获取其 权限信息：用户基本信息、角色、权限.
同时为了支持超级管理员，在入口的鉴权拦截器检测到是 admin 时，就扫描全部权限并添加给 admin 用户。
在这里可以使用缓存权限来加速，但这样会有缓存一致性问题。

用户登录后，访问 url，在 AuthInterceptor 登录，然后将其 权限信息 持久化到 request 的 scope 中
> 这里如果使用 jwt 那就是解析 jwtToken 然后持久化到 request 的 scope 中
> 如果是 session 那就是解析 token 然后持久化到 request 的 scope 中


做权限判断时，一根据 角色 来取到 该角色的权限表 然后在根据权限表有没有这个 权限 来进行判断
权限表达式可以自己设计，一般从左至右是从大到小的顺序，比如 模块:功能:CURD、菜单:按钮:CRUD
这样权限表达式就会被区分为好几种，使用时要注意区分。

Rbac0 其权限是由关联表来赋予的，比如要赋予某个角色权限时只需要更新 角色权限关联表 即可
用户登录，查询该用户所持有的角色，根据角色去取该用户所持有的权限表达式，返回角色跟权限表达式。
前端得到角色跟权限表达式，可以使用 hasRole hasPermission 来决定是否能够访问资源
权限往往有两层，第一层是资源的可见性这个是由于前后端带来的，以前模板引擎是不会有这个的
第二层是资源的 crud，这个是真正的后端权限，决定了该角色能对这个资源做什么
由 第一层 跟 第二层 我们就能得出一个有趣的问题，那就是如果只在前端实现了安全性会怎样？
也就是不想前后端都实现一遍对资源的 curd 判断，只在前端做了显示不显示的判断，那样就会导致安全问题

Spring Security 的设计里，用户和角色都可以拥有权限，譬如在它的 HttpSecurity 接口就同时有着 hasRole()和 hasAuthority()方法，可能刚接触的程序员会疑惑，混淆它们之间的关系。Spring Security 的访问控制模型如图 5-6 所示，可与前面 RBAC 的关系图对比一下。


# 邻接表实现的 父子继承关系，
# 其实相当于单链表，使用时取全局 parent_role_id 为 null 的值然后将其 role 作为 where parent_role_id=role 条件递归往下查
# 一直查到符合条件的值数量为 0，就算是查找完了整个继承关系
# 将结果全部放到 list 中，这样从 0~end 的遍历中就能构造出树了




# 组的概念分两种，一种角色组。一种用户组，都是为了批量分配权限而诞生的方便产物
# 方便产物，要看方便在何处，组的概念方便在于其自动化上：
# 比如 windows 上的权限组，某用户需要 admin 权限时，是将自己加入到 administrator 的组里面，这个组内的用户将会自动获得 admin 权限
# 再比如有几十个角色，需要赋给 几百个人，这时候就可以将这些角色编成一个角色组，加到这个角色组中的用户都自动获得这个组里全部的权限
# 使用用户组是，是将某个角色的权限赋给这个组里面的所有人，比如省行用户组内的所有用户自动获得 admin 权限
# 由上面我们可以开始设计表
# 自动行为需要一个 trigger 的时机，最好只在 role_group_admin、role、user 三个表发生 增删改 的时候，会自动的检查 三个表
# 对于 role 的删、改要涉及到 role_group_admin.roles 的删、改
# 对于 user 的删、改要涉及到  role_group_admin.users 的删、改
# role_group_admin 内 roles、users 的增删改都要求 检查对于的 role、user 表是否存在对应值
# 注意 角色组 跟 用户组 其实可以使用一种表来表达，但是为了简单的区分，最好有不同的名字，也不要混用
# 角色组跟用户组主要体现在操作思路流程的不同上，角色组先建立一堆角色的集合，然后在考虑加入用户，用户组则相反。
# 这两种场景都十分常见，并且不是任何操作者都可以立刻意识到二者的一致性，操作人只是往往是想给一堆用户进行分组，或者想给一堆角色进行分组开始思考的，因此最好区分开来
# 区分开来的两种表正好直接等于两种常见思路，而不需要任何的转换。
# 注意 组 不会影响到继承树的权限，是独立计算的，比如 b 继承自 a，a 加入了 admin 的权限组，b 不会因此获得任何变更。
# 如果是给 a 的 角色用户关联表 添加了 admin 权限，那么 b 就会因为继承关系获得 admin

用户组还可以用来划分数据权限，


前端的实现有多种，一种是将渲染页面的
权限交由后端过滤，前端只负责展示： 前端直接根据后端返回的 menuList 生成 route
后端只返回数据，前端负责过滤（不安全）：前端根据后台返回的 menuList, permissionMenuList 来设计前端的页面 Router


周同学（User）是某 SCI 杂志的审稿人（Role），职责之一是在系统中审核论文（Authority）。在审稿过程（Session）中，当他认为某篇论文（Resource）达到了可以公开发表标准时，就会在后台点击通过按钮（Operation）来完成审核。

本质上，角色是对多种用户的解耦，资源就是对多种资源类型的解耦

菜单树的设计：
菜单本质上是一个树的概念，表达一棵树可以使用很多种方法，由于菜单树几乎不更新，增删改是很少的，这里采用 ClosureTable 的实现方式。
既然表达是结构是树的话，就要考虑一下是支持多棵树还是单颗树，这里的根节点菜单可能有很多个，因此支持多棵树是比较好的。
支持多棵树的话，就要有区分多棵树根节点的方法，同时还要标示出那些节点属于那颗树。

使用 type 来区分是否为根节点，
type=l1:menu:

因此我

current，取当前节点下一级时，current 相当于 parent：where parent=current and deepth=1
取上一级时 current 相当于 child：where child=current and deepth=1



一共有三种权限验证的方式

1、根据是否有此角色来判断
2、根据是否有此权限表达式来判断，比如 xxx.xxx.delete，一般用来表达角色表之类属性

资源的获取：
资源的获取可以从权限表中获得，权限与资源是 1:N 的关系


# 数据权限的设计：
你能访问页面，说明你对这个页面可读。
但是这个页面会返回什么范围内的数据是个问题。

你能看见新增按钮，说明你能新增，这一块由前端控制，不太安全

ruoyi 通过部门来进行数据权限的限制，其表达上就是一颗树，当前节点能看见子树下的全部节点。
一般情况下，想要知道数据权限的实现情况：
有个简单的区分方法，如果是用户组，那就需要在加入用户时提供加入某个用户组的选项，这些选择不管叫什么名，本质上都是用户组的概念，用于对用户进行一个范围的划分。
对应的，角色就是在添加角色时要提供快捷加入某个角色组的选项。
而 ruoyi 框架中的部门跟岗位都是在分配用户时添加上的，因此本质上都是用户组。
不过 ruoyi 将部门与岗位这两个用户组进行了层叠过滤，具体是定义了岗位肯定是所有部门的叶子节点，而部门则是非叶子节点。
这样形成的效果就是，点击部门会进行一次部门组的过滤，点到作为叶子节点的部门时会在部门组过滤的基础上在进行一层岗位的过滤。
因此，点到 a 部门下的 xx 岗位时，就相当于进行了 a 部门的过滤之后，在进行 xx 岗位的过滤。
要注意，用户组本质上只是对用户表的过滤而已，一般不存在其他任何功能。

按照 ruoyi 的思路来实现的，即岗位默认会被所有部门使用，只需要 用户-部门 关联表 即可，否则还需要一个 部门-岗位 表。
ruoyi 这么设计的原因是考虑到用户管理的层面，期望有子级的管理员可以自己添加用户，并且新用户的权限不能超过当前用户的权限。
同理，需要子级管理员可以自己添加 角色 的话，就要设计一个角色树来进行过滤了。
同时，在这种情况下，前端页面显示的就不再是用户表了，而是用户组，也就是部门组的数据
所以 rbac0 与 rbac0withdatascope 的 ui 用户页面显示的数据是不一样的，前者只是对一个表的 crud，后者是对一颗树的 crud。
因此在实现上，要实现比 ruoyi 更好的管理，我们实现 用户-部门 关联表 + 部门-岗位 关联表 + 用户-岗位 关联表即可。

资源分组问题，manu、page、btn 本质上是一组的，特别是在资源多了的情况下，就会需要资源分组了。

数据的权限，本质上就是对树的遍历，不管是对角色进行数据权限的控制，还是对用户进行数据权限的控制，其过滤的方式都是对于当前节点子级的递归遍历罢了。

ruoyi 的 角色-部门 关联表已经有了，是给用户组分配角色，但是似乎没做完，因为前端页面无法给部门分配角色。

ruoyi 的部门是怎么实现树的？
ruoyi 的部门表有 parent_id 和 ancestors，是使用邻接表+路径枚举 共同实现的继承树，也就是用户组的继承树。
ancestors 用于记录祖级信息，也就是从当前节点到顶级节点的路径。


# 不同实现情况对 ui 页面的影响
rbac0 与 rbac0withdatascope 的 ui 用户页面显示的数据是不一样的，前者只是对一个表的 crud，后者是对一颗树的 crud。前者直接展示一个表，后者展示一棵树。


# 从头捋一捋，数据权限的问题：
## 用户登录场景：
1. 前端登录
2. 后端获取用户的所有角色，在从角色组、用户组获取额外的角色，然后根据这些角色去获取所有权限，然后根据权限所指向的资源去取 type 为 menu、page、btn 的数据，并将其构建为 菜单树 返回给前端。
3. 前端获得了用户的 角色、权限、可用的菜单树，根据菜单树构建 路由表，并将 角色、权限 的数据放到 数据中心 中去分发。

## 新增角色场景（数据权限）：
前端访问角色页面，后端取出该用户所持有的资源中包含了 type 为 role 且 el 解析的末尾存在 r 的所有数据，并根据资源的 id 去查询到对于 role 表的数据，返回给前端。
> 规范了返回的范围

前端根据权限是否包含 role:btn:c 判断是否要显示新增按钮
前端点击新增并填写 角色姓名、显示顺序、角色父级（可选，可为该用户的全部角色，不建议实现）、可用的菜单权限（范围为该用户所持有的菜单、页面、按钮）
点击新增发送，后端取出该用户所持有的资源中包含了 type 为 role 且 el 解析的末尾存在 c 的数据，并将新增的角色放到 role 表和、资源表
> 规范了新增的范围

因此这个系统的设计中，角色分配角色，只能权限越分越小。



## 新增用户场景
访问用户页面，



# resource

-- resource 相关的都不提供图形界面，原因在于 resource 与开发的耦合过深，也没有能力做全插件化开发
-- 一种资源一种表，因为资源与资源之间的差异是极大的
-- 打算提供 UI 的话就要求客户端有动态建表的能力，这块能且只能由超级管理员添加

-- resource_permel 说明该资源只用于表达权限，使用起来相当于写死权限，与代码深度耦合



-- ui
-- 页面表，页面的可访问权限由 permission 表管理
-- 所以在添加页面时，要同时添加 permission 和 menu 表
-- 留存前 1000 个 id 作为框架使用



另一种不解耦 resource 的实现方式：
-- 在取用户所有权限时，后端会根据 resource_type 决定如何去取对应的资源，如果 resource_type 是 menu 那就去 menu 表找
-- 前端在拿到用户 permission 后，会查看那些 permission 的 resource_type 是 menu 的
-- 前端将 type 为 menu 的值作为对于菜单栏页面显隐的依据
-- 如需要更高性能，应当分离 permission 与 resourceFile，但此处并不需要多高的性能
-- permission 与 resourceFile 是 1:N 的关系
-- 特别的，按钮是跟后端几乎毫无关系的，只能由前端实现权限控制，因此 按钮 不会有实际的资源
-- 特别的，对于持有 page crud 但是不持有该页面父级 menu 的情况，由于前端不会渲染出这种菜单树，因此不会发生越权访问
-- 注意，对于前后端分离开发而言，页面层次语义的解释权交由前端进行诠释，数据层面语义的解释权交由后端诠释


用户
    无论哪个用户首先它必须是属于某个部门的，部门是行政单位，而某个部门也可以包含多个用户，所以部门和用户的关系为1对多的关系；

先说一下为什么要有用户组的概念，如果有一类的用户都要属于某个角色，我们一个个给用户授予角色，重复工作特别多，所以我们把这么一些用户进行分类，也就是用户组，这样的话，我们直接对用户组赋予角色，减少重复的工作量，这样达到的目的是这，用户拥有的所有许可，就是用户个人所属角色拥有的许可与该用户所在用户组所属角色拥有的许可之和。一个用户可以属于多个用户组，一个用户组也可以包括多个用户，所以用户与用户组是多对多的关系；

角色
    角色是一定数量的许可的集合，许可的载体，一个角色可以包含多个用户，一个用户同样的也可以属于多个角色，所以角色与用户的关系为多对多的关系。同样的一个角色可以包含多个用户组，一个用户组也可以属于多个角色，所以角色和用户组也是多对多的关系；

许可
    许可我一般称它为权限，它包括控制对象和操作，控制对象一般为资源，包括菜单、页面、文件等资源，而操作一般包括增删改查等，图中“系统操作”就是操作，“菜单信息”就是控制对象；

菜单信息中的每个菜单都会有增删改查等操作，所以菜单信息与系统操作是一对多的关系；

我们给角色授予权限时，授予就是颗粒最小的权限，所以我们将系统操作权限授予某些角色。一个角色可以拥有多个系统操作，一个系统操作同样也可以属于多个角色，所以系统操作和角色为多对多的关系。
