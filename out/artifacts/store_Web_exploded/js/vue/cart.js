
var vm = new Vue({
    el: '#cart',
    data: {
        items: null
    },
    methods:{
        getCart:function(){
            console.log(this.items);
            var sef = this;
            //ajax请求
            axios.get('cart.do?method=getCart')
                .then(function (response) {
                    sef.items = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        deleteCartItem:function(pid,index){
            //ajax请求
            axios.get('cart.do?method=delete&pid='+pid)
                .then( (response)=> {
                    this.items.splice(index,1)
                })
                .catch( (error)=> {
                    console.log(error);
                });
        },
        updateCount:function(cartItem,index){
            //ajax请求
            axios.get('cart.do?method=updateCount&pid='+cartItem.pid+"&count="+cartItem.count)
                .then( (response)=> {
                    console.log("ok");
                })
                .catch( (error)=> {
                    console.log(error);
                });
        },
        clear:function(){
            //ajax请求
            axios.get('cart.do?method=clear')
                .then( (response)=> {
                    this.items.splice(0,this.items.length);
                })
                .catch( (error)=> {
                    console.log(error);
                });
        }
    },
    mounted:function () {
        this.$nextTick(function () {
            this.getCart();
        })
    },
    computed:{
        totalPrice:function(){
            var  result= 0;
            if(this.items)
            {
                this.items.forEach(item=>{
                    result+=item.shop_price*item.count
                })
            }
            return  result;
        }
    }
})