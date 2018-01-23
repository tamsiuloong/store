Vue.component('my-nav', {
    template: '<ul class="nav navbar-nav">'+
    '<li v-for="item in items" v-bind:class="{ active: cid==item.id }"><a :href="\'product_list.htm?cid=\'+item.id">{{item.name}}<span class="sr-only">(current)</span></a></li>'+
    '</ul>',
    data:function(){
        return {
            items:{},
            isActive:true,
            cid:''
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

