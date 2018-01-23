var vm = new Vue({
    el: '#app',
    data: {
        username: '',
        password: '',
        yzmCode: '',
        msg:'',
        color:'red'
    },
    methods: {
        login: function () {


            // 发送 POST 请求
            axios({
                method: 'post',
                url: '/user.do?method=login',
                params: {
                    'username': this.username,
                    'password': this.password,
                    'yzmCode': this.yzmCode
                }
            }).then(response => {
                if (response.data.ok) {
                    location.href = 'index.html';
                } else {
                    this.msg = response.data.msg;
                }

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