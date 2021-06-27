import axios from 'axios'

import {
    CODE_RUN_URL
} from './url'


/**
 * 查询统计数据
 */
function codeRunRequest(method, data) {
    return new Promise((resolve, reject) => {
        axios({
            url: CODE_RUN_URL,
            method: method,
            headers: {
                'Content-Type': 'application/json',
                // token: sessionStorage.getItem('token')
            },
            params: {},
            data: data,
        })
        .then((res) => {
            // 成功
            resolve(res.data)
        })
        .catch((res) => {
            // 失败
            reject(res)
        })
    })
}

export default {
	codeRunRequest,
}