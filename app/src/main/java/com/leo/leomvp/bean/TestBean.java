package com.leo.leomvp.bean;

import java.util.List;

/**
 * Created by Leo on 2017/7/23.
 */

public class TestBean {


    /**
     * error : false
     * results : [{"_id":"5971760e421aa90ca209c4af","createdAt":"2017-07-21T11:33:34.104Z","desc":"7-21","publishedAt":"2017-07-21T12:39:43.370Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034ly1fhrcmgo6p0j20u00u00uu.jpg","used":true,"who":"daimajia"},{"_id":"596ffd1e421aa97de5c7c975","createdAt":"2017-07-20T08:45:18.437Z","desc":"7-20","publishedAt":"2017-07-20T15:11:16.10Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fhq25406waj20u00u0b29.jpg","used":true,"who":"daimajia"},{"_id":"596ea620421aa90c9203d3bc","createdAt":"2017-07-19T08:21:52.67Z","desc":"7-19","publishedAt":"2017-07-19T13:23:20.375Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fhovjwwphfj20u00u04qp.jpg","used":true,"who":"代码家"},{"_id":"596d5739421aa97de5c7c959","createdAt":"2017-07-18T08:32:57.632Z","desc":"7-18","publishedAt":"2017-07-18T16:12:55.381Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fhnqjm1vczj20rs0rswia.jpg","used":true,"who":"daimajia"},{"_id":"59681987421aa97de5c7c92f","createdAt":"2017-07-14T09:08:23.898Z","desc":"7 月 17 日","publishedAt":"2017-07-17T12:22:21.307Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fhj5228gwdj20u00u0qv5.jpg","used":true,"who":"带马甲"},{"_id":"596819b7421aa90ca209c45f","createdAt":"2017-07-14T09:09:11.591Z","desc":"RIP","publishedAt":"2017-07-14T13:24:31.177Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fhj53yz5aoj21hc0xcn41.jpg","used":true,"who":"代码家"},{"_id":"5966c4b8421aa90ca209c452","createdAt":"2017-07-13T08:54:16.862Z","desc":"7-13","publishedAt":"2017-07-13T12:28:15.167Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fhhz28n9vyj20u00u00w9.jpg","used":true,"who":"daimajia"},{"_id":"59656ba8421aa97de5c7c91b","createdAt":"2017-07-12T08:22:00.873Z","desc":"7-12","publishedAt":"2017-07-12T13:05:59.766Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fhgsi7mqa9j20ku0kuh1r.jpg","used":true,"who":"daimajia"},{"_id":"59641954421aa90c9203d362","createdAt":"2017-07-11T08:18:28.124Z","desc":"佐佐木希","publishedAt":"2017-07-11T13:46:33.911Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fhfmsbxvllj20u00u0q80.jpg","used":true,"who":"代码家"},{"_id":"5962c411421aa90ca209c425","createdAt":"2017-07-10T08:02:25.353Z","desc":"7-10","publishedAt":"2017-07-10T12:48:49.297Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fhegpeu0h5j20u011iae5.jpg","used":true,"who":"daimajia"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5971760e421aa90ca209c4af
         * createdAt : 2017-07-21T11:33:34.104Z
         * desc : 7-21
         * publishedAt : 2017-07-21T12:39:43.370Z
         * source : chrome
         * type : 福利
         * url : http://ww1.sinaimg.cn/large/610dc034ly1fhrcmgo6p0j20u00u00uu.jpg
         * used : true
         * who : daimajia
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
