import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    usuarioSesion: {
      id: "",
      cedula: "",
      nombres: "",
      apellidos: "",
      estado: "",
      roles: [],
      actas: []
    },
    enSesion: false,

  },
  mutations: {
    SET_USUARIO_SESION(state, usuario) {
      if (usuario.id != "")
        state.enSesion = true
      else state.enSesion = false
      state.usuarioSesion = usuario
    }
  },
  actions: {
    actualizarUsuarioSesion({ commit }, usuario) {
      commit('SET_USUARIO_SESION', usuario)
    }
  },
  modules: {
  },
  getters: {
    rolUsuarioSesion: state => {
      return state.usuarioSesion.roles[0].tipo;
    },
    usuarioEnSesion: state => {
      return state.enSesion;
    },
    nombreUsuarioSesion: state => {
      return state.usuarioSesion.nombres
    }
  }
})
