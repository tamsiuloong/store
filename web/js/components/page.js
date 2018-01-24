Vue.component('my-page', {
    template: '<div style="width:380px;margin:0 auto;margin-top:50px;">'+
    '<ul class="pagination" style="text-align:center; margin-top:10px;">'+
    '<li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>'+
    '<li :class="{ active: p==page.currPage }" v-for="p in page.totalPage"><a href="#">{{p}}</a></li>'+
    '<li>'+
    '<a href="#" aria-label="Next">'+
    '<span aria-hidden="true">&raquo;</span>'+
    '</a>'+
    '</li>'+
    '</ul>'+
    '</div>',
    props: ['page'],
    data:function(){
        return {

        }
    },
    methods:{
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
    mounted:function(){
        this.$nextTick(function () {
            this.cid = this.getQueryVariable("cid");
        })



        //ajax请求
        axios.get('category.do', {
            params: {
                'method': 'findAll'
            }
        }).then( (response)=> {
            if(response.data.ok){
                this.items = response.data.data;
            }
        });
    }
})

