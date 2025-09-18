
const myAxios = axios.create({
    timeout: 10000
});
myAxios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
console.log()
myAxios.interceptors.request.use(config => {
    console.log("请求拦截器")
    return config;
});

window.myAxios = myAxios;


