<script setup lang="ts">
import { jobInfoType, jobLogType, PagesWrap, UrlJob } from '@/types/global';
import { HttpTool } from '@/utils/net/http/request';
// import * as tMl from '@/types/global';

// console.log("tMl", tMl);


export interface ITablePagination {
    currentPage?: number,
    pageSize?: number,
    pageSizeOpt?: number[],
    paginationDisabled?: boolean,
    dataTotal?: number,
}


export interface ITable {
    pagination?: ITablePagination,
    table: {
        tableHead?: {
            colText: string;
            key: string;
        }[],
        tableData: jobLogType[] | jobInfoType[] 
    },
}


const props = withDefaults(
    defineProps<ITable>(),
    {
        pagination: () => ({
            currentPage: 1,
            pageSize: 10,
            pageSizeOpt: [10, 25, 50, 100, 500],
            paginationDisabled: false,
            dataTotal: 0,
        })
    }
)

const pagination = props.pagination;
const table = props.table;

const emit = defineEmits(['update:pagination'])

const handleCurrentChange = (currentPage: number) => {
    emit('update:pagination', { ...pagination, currentPage })
}

const handleSizeChange = (pageSize: number) => {
    emit('update:pagination', { ...pagination, pageSize })
}

// example T = jobLogType[]
const getPageData = async <T = any[]>(searchForm: {
    executorSelect: string;
    missionSelect: string;
    stateSelect: string;
    current: number;
    size: number;
}, filterHandle?: (data: T) => T) => {
    let resPageData = await HttpTool.get<PagesWrap<T>>(UrlJob.log_fetchListByPage, searchForm)
    table.tableData = resPageData.records
    if(filterHandle) {
        table.tableData = filterHandle(table.tableData)
    }
    pagination.dataTotal = resPageData.total
}

defineExpose({
    getPageData
})
</script>





<template>
    <el-row justify="space-between">
        <slot name="leftHeader"></slot>
        <el-pagination
            v-if="!pagination.paginationDisabled"
            v-model:currentPage="pagination.currentPage"
            :page-sizes="pagination.pageSizeOpt"
            :page-size="pagination.pageSize"
            :disabled="pagination.paginationDisabled"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pagination.dataTotal"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
        ></el-pagination>
    </el-row>

    <el-table border :data="table.tableData" style="width: 100%">
        <template v-for="(headItem, index) in table.tableHead" :key="index">
            <el-table-column align="center" :prop="headItem.key" :label="headItem.colText">
                <template #default="{ row, column }">
                    <slot :name="headItem.key" :row="row">{{ row[headItem.key] }}</slot>
                </template>
            </el-table-column>
        </template>
    </el-table>
</template>



<style>
.el-table .warning-row {
    --el-table-tr-bg-color: var(--el-color-warning-lighter);
}
.el-table .success-row {
    --el-table-tr-bg-color: var(--el-color-success-lighter);
}
</style>

<style scoped>
.demo-pagination-block + .demo-pagination-block {
    margin-top: 10px;
    margin-left: auto;
}
.demo-pagination-block .demonstration {
    margin-bottom: 16px;
}
</style>