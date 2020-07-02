import axios from 'axios'

axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
axios.defaults.headers.common['Access-Control-Allow-Headers'] = 'Origin, X-Requested-With, Content-Type, Accept';
axios.defaults.withCredentials = true;
axios.defaults.crossDomain = true;

const apiServidor = axios.create({
    baseURL: 'http://localhost:8080',
})

export default {
    login(formulario) {
        let formData = new FormData();
        formData.set("username", formulario.username);
        formData.set("password", formulario.password);
        apiServidor.post('/login', formData, { headers: { 'Content-Type': 'application/x-www-form-urlencoded' } })
            .then((result) => {
                this.loginSuccessful(result);
            })
            .catch((error) => {
                this.loginFailed();
            })
    },

    getActas() {
        return apiServidor.get('/acta/ver/todas')
    }
}