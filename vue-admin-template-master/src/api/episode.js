import request from '@/utils/request'


export default {

  //添加episode
  addEpisode(episode) {
    return request({
      url: `/service/episode/addEpisode`,
      method: 'post',
      data:episode
    })
  },
  //删除episode
  deleteEpisode(episodeId) {
    return request({
      url: `/service/episode/delete/${episodeId}`,
      method: 'delete',
    })
  },

}
