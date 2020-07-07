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
                console.log(error.data);
            })
    },

    getActas() {
        return apiServidor.get('/acta/ver/todas')
    },
    getActa(id) {
        return apiServidor.get('/acta/ver/' + id)
    },
    getActasSecretario() {
        return apiServidor.get('/acta/ver/decanato')
    },
    guardarActa(Acta) {
        let formData = new FormData();
        formData.append("tipo", Acta.tipo);
        formData.append("fecha", Acta.fecha);
        formData.append("descripcion", Acta.descripcion);
        formData.append("archivo", Acta.archivo, "Acta-Sesion-" + Acta.tipo + "-" + Acta.fecha + ".pdf");

        var headers = {
            'Content-Type': 'multipart/form-data',
        };

        apiServidor.post('/acta/guardar', formData, headers)
    },


    getDecanatos() {
        return apiServidor.get('/decanato/ver/todos')
    },
    getDecanato(id) {
        return apiServidor.get('/decanato/ver/' + id)
    },
    guardarDecanato(Decanato) {
        return apiServidor.post('/decanato/guardar', Decanato)
            .catch((error) => {
                console.log(error.response)
            })
    },

    getUsuario(id) {
        return apiServidor.get('/usuario/ver/' + id)
    },
    getUsuarios() {
        return apiServidor.get('/usuario/ver/todos')
    },
    getUsuarioSesion() {
        return apiServidor.get('/usuario/sesion')
    },
    guardarUsuario(Usuario) {
        return apiServidor.post('/usuario/guardar', Usuario)
            .catch((error) => {
                console.log(error.response)
            })
    },




}