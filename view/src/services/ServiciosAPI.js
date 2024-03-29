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
        return apiServidor.post('/login', formData, { headers: { 'Content-Type': 'application/x-www-form-urlencoded' } })
    },

    logout() {
        return apiServidor.post('/logout')
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
        formData.append("archivo", Acta.archivo);

        var headers = {
            'Content-Type': 'multipart/form-data',
        };

        return apiServidor.post('/acta/guardar', formData, headers)
    },
    editarActa(Acta) {
        let formData = new FormData();
        formData.append("id", Acta.id);
        formData.append("tipo", Acta.tipo);
        formData.append("fecha", Acta.fecha);
        formData.append("descripcion", Acta.descripcion);
        formData.append("archivo", Acta.archivo);
        formData.append("padre", Acta.padre);

        var headers = {
            'Content-Type': 'multipart/form-data',
        };

        return apiServidor.post('/acta/editar', formData, headers)
    },
    getPDFActa(id) {
        return apiServidor.get('/acta/descargar/' + id)
    },
    eliminarActa(id) {
        return apiServidor.delete('/acta/eliminar/' + id)
    },
    getEstatusActa(id) {
        return apiServidor.get('/acta/estatus/' + id)
    },
    getEstatusActaReporte(id) {
        return apiServidor.get('/acta/reporte/estatus/' + id)
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
    getReporteDecanato(anno, mes) {
        return apiServidor.get('/decanato/reporte/' + anno + '/' + mes)
    },
    eliminarDecanato(id) {
        return apiServidor.delete('/decanato/eliminar/' + id)
    },






    getUsuario(id) {
        return apiServidor.get('/usuario/ver/' + id)
    },
    getUsuarioId(id) {
        return apiServidor.get('/usuario/encontrar/' + id)
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
    eliminarUsuario(id) {
        return apiServidor.delete('/usuario/eliminar/' + id)
    },




    getEstatuses() {
        return apiServidor.get('/estatus/ver/todos')
    },
    getEstatus(id) {
        return apiServidor.get('/estatus/ver/' + id)
    },
    guardarEstatus(Decanato) {
        return apiServidor.post('/estatus/guardar', Decanato)
    },
    getReporteEstatus(anno, mes) {
        return apiServidor.get('/estatus/reporte/' + anno + '/' + mes)
    },
    eliminarEstatus(id) {
        return apiServidor.delete('/estatus/eliminar/' + id)
    },
}