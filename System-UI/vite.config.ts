import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { resolve } from "path"

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      "@": resolve(__dirname, "./src")
    }
  },
  server: {
    cors: {
    origin: [/.*127.0.0.1.*/, /.*localhost.*/],
      allowedHeaders: ["Content-Type"],
      credentials: true
    },
    host: '0.0.0.0',
    port: 8999
  },
  base: "/system-rbac/",
  build: {
    outDir: "../deploy/tomcat/webapps/system-rbac"
  },
}) 
