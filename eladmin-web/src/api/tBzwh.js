import request from '@/utils/request'
// 统一请求路径前缀在libs/axios.js中修改
import { getRequest, postRequest, putRequest, deleteRequest, importRequest, uploadFileRequest } from '@/libs/axios';


export function add(data) {
  return request({
    url: 'api/tBzwh',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'api/tBzwh/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'api/tBzwh',
    method: 'put',
    data
  })
}

export function get(data) {
  return getRequest(
    '/tBzwh/getTBzwhsBypage',
    data
  );
}

export function getDeptsByPid(params) {
  return request({
    url: 'api/dept/getDeptsByPid',
    method: 'get',
    params
  })
}
export function getTBzwhsById(params) {
  return request({
    url: 'api/tBzwh/getTBzwhsById',
    method: 'get',
    params
  })
}

export default { get,add, edit, del,getDeptsByPid ,getTBzwhsById}
