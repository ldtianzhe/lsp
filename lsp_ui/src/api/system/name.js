import request from '@/utils/request'

// 查询实名信息列表
export function listName(query) {
  return request({
    url: '/system/name/list',
    method: 'get',
    params: query
  })
}

// 查询实名信息详细
export function getName(id) {
  return request({
    url: '/system/name/' + id,
    method: 'get'
  })
}

// 新增实名信息
export function addName(data) {
  return request({
    url: '/system/name',
    method: 'post',
    data: data
  })
}

// 修改实名信息
export function updateName(data) {
  return request({
    url: '/system/name',
    method: 'put',
    data: data
  })
}

// 删除实名信息
export function delName(id) {
  return request({
    url: '/system/name/' + id,
    method: 'delete'
  })
}

// 导出实名信息
export function exportName(query) {
  return request({
    url: '/system/name/export',
    method: 'get',
    params: query
  })
}