

var vm = new Vue({
    el: '#app',
    data: {
        msg:'',
        username:'',
        password:'',
        password2:'',
        email:'',
        birthday:'',
        yzmCode:'',
        name:'',
        sex:'',
        color:'black'
    },
    methods: {
        checkUsername: function () {
            //ajax请求
            axios.get('user.do', {
                params: {
                    'method': 'checkUsername',
                    'username':this.username
                }
            }).then( (response)=> {
                    //alert(data);
                    if(response.data){
                        this.msg='该账号已被使用';
                        this.color="red";
                    }
                    else{
                        this.msg='该账号可以使用';
                        this.color="black";
                    }
                });
        },
        register:function(){
            if(this.password!=this.password2)
            {
                this.msg="密码不一致";
                this.color="red";
                return false;
            }
            // 发送 POST 请求
            axios({
                method: 'post',
                url: '/user.do?method=register',
                params: {
                    username:this.username,
                    password:this.password,
                    password2:this.password2,
                    email:this.email,
                    birthday:this.birthday,
                    yzmCode:this.yzmCode,
                    name:this.name,
                    sex:this.sex
                }
            }).then(response=>{
                location.href='index.html';
            });
        }
    },
    mounted: function () {
        this.$nextTick(function () {
            //init code
        })
    },
    computed: {}
})