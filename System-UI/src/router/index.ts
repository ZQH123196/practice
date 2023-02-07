import { createRouter, createWebHashHistory, NavigationGuardNext, RouteLocationNormalized, RouteRecord, RouteRecordRaw } from "vue-router";
import layout from '@/layout/index.vue';
import dashboard from '@/views/system/dashborad/dashboard.vue';
import jobLog from '@/views/system/schedule/jobLog/jobLog.vue';
import executorManager from '@/views/system/schedule/executorManager/executorManager.vue';


const testRoute: RouteRecordRaw[] = [
    {
        path: "/test",
        redirect: "/test/jobLog",
    },
    {
        path: "/test/jobLog",
        name: "testjobLog",
        component: jobLog
    },
    {
        path: "/test/executorManager",
        name: "testexecutorManager",
        component: executorManager
    },
]

const routes: RouteRecordRaw[] = [
    ...testRoute,
    {
        path: "/",
        redirect: "/system/dashboard",
    },
    {
        path: "/system",
        component: layout,
        children: [
            {
                path: "dashboard",
                name: "dashboard",
                component: dashboard
            }
        ]
    },
    {
        path: "/system/schedule",
        component: layout,
        children: [
            {
                path: "jobLog",
                name: "jobLog",
                component: jobLog
            }
        ]
    },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes: routes
});



router.beforeEach((to: RouteLocationNormalized, from: RouteLocationNormalized, next: NavigationGuardNext) => {
    console.log(`${from.path} => ${to.path}`);

    next()

})



export {
    router
};




