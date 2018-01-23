var vm = new Vue({
    el: '#app',
    data: {
        hotProductList: {}
    },
    methods: {
        init: function () {
            // 发送 POST 请求
            axios({
                method: 'get',
                url: '/product.do',
                params: {
                    'method': 'getHotProductList'
                }
            }).then(response => {
                if (response.data.ok) {
                    this.hotProductList = response.data.data;
                } else {
                    console.error("no data error")
                }

            });

        }
    },
    mounted: function () {
        this.$nextTick(function () {
            this.init();
        })
    },
    computed: {}
})