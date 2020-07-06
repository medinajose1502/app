import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    usuarioSesion: {
      cedula: "",
      contrasenna: "",
      nombres: "",
      apellidos: "",
      roles: [],
      decanato: {
        id: ""
      },
      actas: []
    }

  },
  mutations: {
    USUARIO_SESION(state, usuario) {
      state.usuarioSesion = usuario
    }
  },
  actions: {
    actualizarUsuarioSesion({ commit }, usuario) {
      commit('USUARIO_SESION', usuario)
    }
  },
  modules: {
  }
})
