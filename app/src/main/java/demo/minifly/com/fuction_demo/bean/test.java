package demo.minifly.com.fuction_demo.bean;

/**
 * author ：minifly
 * date: 2017/9/7
 * time: 16:37
 * desc:
 */
public class test {

    /**
     * response : {"state":"approved","id":"PAY-86S95508WP919944NLGYQIHI","create_time":"2017-09-07T08:32:49Z","intent":"sale"}
     * client : {"platform":"Android","paypal_sdk_version":"2.15.3","product_name":"PayPal-Android-SDK","environment":"sandbox"}
     * response_type : payment
     */

    private ResponseBean response;
    private ClientBean client;
    private String response_type;

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public ClientBean getClient() {
        return client;
    }

    public void setClient(ClientBean client) {
        this.client = client;
    }

    public String getResponse_type() {
        return response_type;
    }

    public void setResponse_type(String response_type) {
        this.response_type = response_type;
    }

    public static class ResponseBean {
        /**
         * state : approved
         * id : PAY-86S95508WP919944NLGYQIHI
         * create_time : 2017-09-07T08:32:49Z
         * intent : sale
         */

        private String state;
        private String id;
        private String create_time;
        private String intent;

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getIntent() {
            return intent;
        }

        public void setIntent(String intent) {
            this.intent = intent;
        }
    }

    public static class ClientBean {
        /**
         * platform : Android
         * paypal_sdk_version : 2.15.3
         * product_name : PayPal-Android-SDK
         * environment : sandbox
         */

        private String platform;
        private String paypal_sdk_version;
        private String product_name;
        private String environment;

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getPaypal_sdk_version() {
            return paypal_sdk_version;
        }

        public void setPaypal_sdk_version(String paypal_sdk_version) {
            this.paypal_sdk_version = paypal_sdk_version;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getEnvironment() {
            return environment;
        }

        public void setEnvironment(String environment) {
            this.environment = environment;
        }
    }
}
