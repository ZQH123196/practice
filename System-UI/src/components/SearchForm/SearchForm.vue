<script lang="ts" setup>
import { Enum_IFormType } from '@/base-ui/form/index';
import MyForm from '@/base-ui/form/index'
import { ref } from 'vue';
import { ISearchFormConfig } from '.';

const props = withDefaults(
  defineProps<{
    searchFormConfig: ISearchFormConfig
  }>(),
  {}
)


const formConfigs = props.searchFormConfig.formConfig


const modelKeys = formConfigs.map(it => ({ key: it.child.modelKey, defaultValue: it.child?.modelDefault }))
const initFormModelValue = {}
// props 值检查
// 如果有默认值就用默认值，没有默认值就用 空字符串
for (let it of modelKeys) {
  initFormModelValue[it.key] = it.defaultValue ?? ""
}
// modelValue 必须要包裹响应式
const modelValue = ref(initFormModelValue)


const emit = defineEmits(['clickSearchBtn', 'clickResetBtn'])

</script>



<template>
  <el-row>
    <my-form :form-configs="formConfigs" v-model="modelValue">
      <template #footer="scope">
        <div style="margin-left: auto; margin-right: 10px;">
          <el-button @click="emit('clickSearchBtn')" type="primary">搜索</el-button>
          <el-button @click="emit('clickResetBtn')" type="primary">重置</el-button>
        </div>
      </template>
    </my-form>
  </el-row>
</template>



<style scoped>
</style>
