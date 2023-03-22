import request from '@/utils/request'


export default {
  //根据影视id获得季和集的列表
  getSeasonsAndEpisodes(videoID) {
    return request({
      url: `/service/season/getSeason/${videoID}`,
      method: 'get',
    })
  },
  //添加season
  addSeason(season) {
    return request({
      url: `/service/season/addSeason`,
      method: 'post',
      data:season
    })
  },
  //根据seasonId查询season
  getSeasonById(seasonId) {
    return request({
      url: `/service/season/getSeasonInfoById/${seasonId}`,
      method: 'get',
    })
  },
  //修改season
  updateSeason(season) {
    return request({
      url: `/service/season/updateSeason`,
      method: 'post',
      data:season
    })
  },
  //删除season
  deleteSeason(seasonId) {
  return request({
    url: `/service/season/deleteById/${seasonId}`,
    method: 'delete',
  })
  },
}
