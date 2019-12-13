import request from '@/utils/request'

// 查询任务信息列表
export function listMission(query) {
  return request({
    url: '/system/mission/list',
    method: 'get',
    params: query
  })
}

// 查询任务信息详细
export function getMission(id) {
  return request({
    url: '/system/mission/' + id,
    method: 'get',
  })
}

// 新增任务信息
export function addMission(data) {
  return request({
    url: '/system/mission',
    method: 'post',
    data: data
  })
}

// 修改任务信息
export function updateMission(data) {
  return request({
    url: '/system/mission',
    method: 'put',
    data: data
  })
}

// 删除任务信息
export function delMission(id) {
  return request({
    url: '/system/mission/' + id,
    method: 'delete'
  })
}

// 导出任务信息
export function exportMission(query) {
  return request({
    url: '/system/mission/export',
    method: 'get',
    params: query
  })
}
