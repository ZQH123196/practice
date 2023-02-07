

export enum UrlJob {
    group_fetchList = "/rest/job-group/fetchList",
    info_fetchList = "/rest/job-info/fetchList",
    log_fetchList = "/rest/job-log/fetchList",
    log_fetchListByPage = "/rest/job-log/fetchListByPage",

    info_fetchListByPage = "/rest/job-info/fetchListByPage",
    info_triggerOnce = "/jobinfo/trigger",
    info_startJob = "/jobinfo/start",
    info_stopJob = "/jobinfo/stop",

    
}


export interface PagesWrap<T> {
    records: T;
    total: number;
    size: number;
    current: number;
    orders: any[];
    optimizeCountSql: boolean;
    searchCount: boolean;
    countId: null;
    maxLimit: null;
    pages: number;
}


export type jobGroupType = {
    id: number;
    appname: string;
    title: string;
    addressType: number;
    addressList: string;
    updateTime: string;
    registryList: string[];
}



export type jobInfoType = {
    id: number;
    jobGroup: number;
    jobDesc: string;
    addTime: string;
    updateTime: string;
    author: string;
    alarmEmail: string;
    scheduleType: string;
    scheduleConf: string;
    misfireStrategy: string;
    executorRouteStrategy: string;
    executorHandler: string;
    executorParam: string;
    executorBlockStrategy: string;
    executorTimeout: number;
    executorFailRetryCount: number;
    glueType: string;
    glueSource: string;
    glueRemark: string;
    glueUpdatetime: string;
    childJobId: string;
    triggerStatus: number | string;
    triggerLastTime: number;
    triggerNextTime: number;
}

export interface jobInfoTableType extends jobInfoType {
    extraOptional: string
}


export type jobLogType = {
    id: number;
    jobGroup: number;
    jobId: number;
    executorAddress: null | string;
    executorHandler: string;
    executorParam: string;
    executorShardingParam: null;
    executorFailRetryCount: number;
    triggerTime: string;
    triggerCode: number;
    triggerMsg: string;
    handleTime: null | string;
    handleCode: number;
    handleMsg: null | string;
    alarmStatus: number;
}
















