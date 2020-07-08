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
        var respuesta;
        let formData = new FormData();
        formData.set("username", formulario.username);
        formData.set("password", formulario.password);
        return apiServidor.post('/login', formData, { headers: { 'Content-Type': 'application/x-www-form-urlencoded' } })
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
        if (Acta.archivo != null)
            formData.append("archivo", Acta.archivo);

        var headers = {
            'Content-Type': 'multipart/form-data',
        };

        return apiServidor.post('/acta/guardar', formData, headers)
    },


    getDecanatos() {
        return apiServidor.get('/decanato/ver/todos')
    },
    getDecanato(id) {
        return apiServidor.get('/decanato/ver/' + id)
    },
    guardarDecanato(Decanato) {
        return apiServidor.post('/decanato/guardar', Decanato)
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
    },




}