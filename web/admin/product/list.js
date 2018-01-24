var vm = new Vue({
    el: '#app',
    data: {
        page:{}
    },
    methods: {
        init: function (pageNo) {
            //ajax请求
            axios.get('../../admin/product.do', {
                params: {
                    'method': 'findByPage',
                    'currPage':pageNo
                }
            }).then( (response)=> {
                if(response.data.ok){
                    this.page = response.data.data;
                }
            });
        }
    },
    mounted: function () {
        this.$nextTick(function () {
            this.init(1);
        })
    },
    computed: {}
})