import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import { router } from '@/router'

const app = createApp(App)

app.use(ElementPlus)
app.use(router)



/**
 * 如果有 wujie 上下文，就控制器 mount 跟 unmount 的逻辑
 */
if (window.__POWERED_BY_WUJIE__) {
    window.__WUJIE_MOUNT = () => {
        app.mount('#app');
    };
    window.__WUJIE_UNMOUNT = () => {
        app.unmount();
    };
    /*
        由于vite是异步加载，而无界可能采用fiber执行机制
        所以mount的调用时机无法确认，框架调用时可能vite
        还没有加载回来，这里采用主动调用防止用没有mount
        无界mount函数内置标记，不用担心重复mount
    */
   // 注册完成之后主动调用一次 mount
    window.__WUJIE.mount()
} else {
    app.mount('#app');
}


