package com.example.taobaounion.model.domain;

import java.util.List;

/**
 * @ProjectName: TaobaoUnion
 * @Package: com.example.taobaounion.model
 * @ClassName: Categories
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020-02-26 11:17
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020-02-26 11:17
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class Categories {

    /**
     * success : true
     * code : 10000
     * message : 获取分类成功.
     * data : [{"id":9660,"title":"推荐"},{"id":9649,"title":"食品"}]
     */

    private boolean success;
    private int code;
    private String message;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 9660
         * title : 推荐
         */

        private int id;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    '}';
        }
    }
}
