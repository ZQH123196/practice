<script setup lang="ts">
import { Enum_IFormType } from '../types';


const props = withDefaults(
    defineProps<{
        formConfigs: any[],
        // vue2 此处的值应为 value
        modelValue: any
    }>(), {

}
)



const emit = defineEmits(["update:modelValue"])
const handleModalValueUpdate = (key, value) => {
    const newValue = { ...props.modelValue, [key]: value }
    emit('update:modelValue', newValue)
}

</script>



<template>
    <el-form>
        <el-form-item>
            <slot name="header"></slot>
        </el-form-item>
        <!-- 这么设计是为了支持在一行内有多个 el-form-item，因为其默认是占据一行的 -->
        <el-row>
            <template v-for="(item) in props.formConfigs">
                <el-col :span="item.span">
                    <el-form-item v-bind="item.child.formItemProps" style="margin: 5px 10px 5px 10px;">
                        <template v-if="item.child.type == Enum_IFormType.input">
                            <el-input
                            style="width: 100%"
                                :model-value="props.modelValue[item.child.modelKey]"
                                @update:model-value="handleModalValueUpdate(item.child.modelKey, $event)"
                                v-bind="item.child.formItemTagetProps"
                            ></el-input>
                        </template>
                        <template v-else-if="item.child.type == Enum_IFormType.select">
                            <el-select
                            style="width: 100%"
                                :model-value="props.modelValue[item.child.modelKey]"
                                @update:model-value="handleModalValueUpdate(item.child.modelKey, $event)"
                                v-bind="item.child.formItemTagetProps"
                            >
                                <el-option
                                    v-for="(option) in item.child.formItemTagetProps.options"
                                    :key="option.value"
                                    :label="option.label"
                                    :value="option.value"
                                />
                            </el-select>
                        </template>
                        <template v-else-if="item.child.type == Enum_IFormType.datepicker">
                            <el-date-picker
                            style="width: 100%"
                                :model-value="props.modelValue[item.child.modelKey]"
                                @update:model-value="handleModalValueUpdate(item.child.modelKey, $event)"
                                v-bind="item.child.formItemTagetProps"
                            ></el-date-picker>
                        </template>
                    </el-form-item>
                </el-col>
            </template>
        </el-row>
        <el-form-item>
            <slot name="footer"></slot>
        </el-form-item>
    </el-form>
</template>