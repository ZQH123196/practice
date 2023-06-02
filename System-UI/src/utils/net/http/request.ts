import axios, { AxiosInstance, AxiosRequestConfig, AxiosRequestHeaders, AxiosResponse } from "axios";
import qs from 'qs';
import { UrlJob } from "@/types/global";
import { ElMessage } from "element-plus";



// 只要后端不用 cookie 做验证就自动解决了 CSRF 问题
const defaultConfig: AxiosRequestConfig = {
    baseURL: import.meta.env.VITE_APP_BASE_API as string,
    timeout: 10000,
    headers: {
        "Accept": "application/json, text/plain, */*",
        "Content-Type": "application/json",
    },
    // 选用最严格的序列化，避免嵌套信息丢失
    paramsSerializer: (params: any) => qs.stringify(params, { arrayFormat: "indices" })
}

const checkHttpSuccess = (code: number) => {
    if (code == 200) return true
    return false
}

class HttpFactory {
    private axiosInstance: AxiosInstance;

    constructor(axiosInstance: AxiosInstance) {
        this.axiosInstance = axiosInstance;

    }

    public get<T>(
        url: UrlJob,
        params?: Object,
        headers?: AxiosRequestHeaders
    ): Promise<T> {
        return new Promise((resolve, reject) => {
            this.axiosInstance.get<T>(url, { params, headers }).then((res) => {
                resolve(res.data)
            }).catch((err) => {
                console.log("err", err);
                reject(err)
            })
        })
    }

    public async post<T>(
        url: UrlJob,
        data?: any,
        params?: Object,
        headers?: AxiosRequestHeaders
    ): Promise<T> {
        const res = await this.axiosInstance.post(url, data, { params, headers })
        if (!checkHttpSuccess(res.status)) {
            ElMessage({
                
            })
        }
        return res.data
    }



}



const axiosBasic = axios.create(defaultConfig)

// TODO Token, errCode handle
axiosBasic.interceptors.request.use((config: AxiosRequestConfig) => {
    return config
})
axiosBasic.interceptors.response.use((config: AxiosRequestConfig) => {
    return config
})



export const HttpTool = new HttpFactory(axiosBasic)




