import request from '@/utils/request'


export default {
  //根据影视id获得季和集的列表
  getSeasonsAndEpisodes(videoID) {
    return request({
      url: `/service/season/getSeason/${videoID}`,
      method: 'get',
    })
  }

}
