一、关于百度歌曲
1. 百度音乐开发平台 http://open.music.baidu.com/
2. 集成的一般步骤: 申请成为百度开发者--创建应用--下载SDK-初始化SDK
3. 百度歌曲下载:
    1.主地址
    http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.6.5.6&format=json
    2.下载列表地址:
    &method=baidu.ting.billboard.billList&type=1&size=10&offset=0
    3.搜索歌曲:
    method=baidu.ting.search.catalogSug&query=海阔天空
    4.播放:
    method=baidu.ting.song.playAAC&songid=877578
    5.LRC歌词
    method=baidu.ting.song.lry&songid=877578
    6.推荐列表
    method=baidu.ting.song.getRecommandSongList&song_id=877578&num=5
    7.下载
    method=baidu.ting.song.downWeb&songid=877578&bit=24&_t=1393123213
    8.获取歌手信息
    method=baidu.ting.artist.getInfo&tinguid=877578
    9.获取歌手歌曲列表
    method=baidu.ting.artist.getSongList&tinguid=877578&limits=6&use_cluster=1&order=2