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
  //根据集id找到episode,回显
  getEpisodeById(id){
    return request({
      url: `/service/episode/getEpisodeById/${id}`,
      method: 'get',
    })
  },
  //修改episode
  updateEpisode(episode){
    return request({
      url: `/service/episode/updateEpisode`,
      method: 'post',
      data:episode
    })
  }

}
