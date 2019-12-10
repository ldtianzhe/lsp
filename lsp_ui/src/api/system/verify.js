import request from '@/utils/request'

// 查询审核信息列表
export function listVerify(query) {
  return request({
    url: '/system/verify/list',
    method: 'get',
    params: query
  })
}

// 查询审核信息详细
export function getVerify(verifyId) {
  return request({
    url: '/system/verify/' + verifyId,
    method: 'get'
  })
}

// 新增审核信息
export function addVerify(data) {
  return request({
    url: '/system/verify',
    method: 'post',
    data: data
  })
}

// 修改审核信息
export function updateVerify(data) {
  return request({
    url: '/system/verify',
    method: 'put',
    data: data
  })
}

// 删除审核信息
export function delVerify(verifyId) {
  return request({
    url: '/system/verify/' + verifyId,
    method: 'delete'
  })
}

// 导出审核信息
export function exportVerify(query) {
  return request({
    url: '/system/verify/export',
    method: 'get',
    params: query
  })
}