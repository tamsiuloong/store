var vm = new Vue({
    el: '#app',
    data: {
        productList:{},
        cid:''
    },
    methods: {
        init: function () {
            this.cid = this.getQueryVariable("cid");
            //ajax请求
            axios.get('product.do', {
                params: {
                    'method': 'getProductListByCid',
                    'cid':this.cid
                }
            }).then( (response)=> {
                if(response.data.ok){
                    this.productList = response.data.data.list;
                }
            });
        },
        getQueryVariable:function(variable)
        {
            var query = window.location.search.substring(1);
            var vars = query.split("&");
            for (var i=0;i<vars.length;i++) {
                var pair = vars[i].split("=");
                if(pair[0] == variable){return pair[1];}
            }
            return(false);
        }
    },
    mounted: function () {
        this.$nextTick(function () {
            this.init();
        })
    },
    computed: {}
})