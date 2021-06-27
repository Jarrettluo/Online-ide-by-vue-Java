<template>
    <div class="panel">
        <div class="panel-heading">
            <el-button type="success" round @click="postCode">RUN</el-button>
            <el-button round icon="el-icon-delete" @click="clearCode">清空</el-button>
        </div>
        <div class="panel-body">
            <el-row>
                <el-col :span="15" style="padding-right:5px;">
                    <About ref="codeEditor"/>
                </el-col>
                <el-col :span="9" style="padding-left:5px;">
                    <textarea v-model="resultText" rows="18" placeholder="输出接口" style="background-color:#002240;color:#eee;"></textarea>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
import About from "./About.vue"
import runPageRequest from "../request/requests"

export default {
    data() {
        return {
            resultText: null,
        }
    },
    components: {
        About
    },
    mounted() {

    },
    methods: {
        /**
         * @description 点击提交以后的请求
         * @author 罗佳瑞
         * @since 2021年6月25日
         */
        async postCode() {
            let code = this.$refs.codeEditor.code
            if( code == null) return;
            await runPageRequest.codeRunRequest("POST", {code: code})
                .then(res => {
                    this.updateCodeRun(res)
                })
                .catch(res => {
                    console.log(res)
                })
        },
        updateCodeRun(res){
            this.resultText = res
        },
        /**
         * @description 清除代码
         */
        clearCode() {
            this.$refs.codeEditor.initCode()
            this.resultText = null
        }

    }
}
</script>

<style lang="scss" scoped>
    .panel {
        margin-bottom: 20px;
        background-color: #fff;
        border: 1px solid #ddd;
        border-radius: 4px;
        -webkit-box-shadow: 0 1px 1px rgba(0,0,0,.05);
        box-shadow: 0 1px 1px rgba(0,0,0,.05);
        box-sizing: border-box;
        text-align: left;
        max-width: 1200px;
        min-width: 980px;
        margin: auto;
        .panel-heading {
            padding: 10px 15px;
            border-bottom: 1px solid transparent;
            border-top-left-radius: 3px;
            border-top-right-radius: 3px;
            color: #333;
            background-color: #f5f5f5;
            border-color: #ddd;
        }
        .panel-body {
            padding: 15px;
            textarea {
                background-color: #333;
                box-sizing: border-box;
                display: block;
                width: 100%;
                height: 340px;
                padding: 6px 12px;
                font-size: 14px;
                line-height: 1.42857143;
                color: #fff;
                background-color: #fff;
                background-image: none;
                border: 1px solid #ccc;
                border-radius: 4px;
                -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
                box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
                -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
                -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
                transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
            }
        }
    }
</style>