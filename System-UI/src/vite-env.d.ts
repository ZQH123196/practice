/// <reference types="vite/client" />

declare module '*.vue' {
  import type { DefineComponent } from 'vue'
  const component: DefineComponent<{}, {}, any>
  export default component
}


interface Window {
  // 解决上下中报错没有类型问题
  $wujie: any; 
  // 是否存在无界
  __POWERED_BY_WUJIE__?: boolean;
  // 子应用mount函数
  __WUJIE_MOUNT: () => void;
  // 子应用unmount函数
  __WUJIE_UNMOUNT: () => void;
  // 子应用无界实例
  __WUJIE: { mount: () => void };
}