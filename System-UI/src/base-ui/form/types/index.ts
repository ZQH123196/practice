export enum Enum_IFormType {
    input = 'input',
    password = 'password',
    select = 'select',
    datepicker = 'datepicker'
}




export interface IFormItem {
    span: number
    child: {
        type: Enum_IFormType,
        modelKey: string,
        modelDefault: any,
        formItemProps: {
            label: string
        },
        formItemTagetProps: any,
    }
}




