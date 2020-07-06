import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    usuario: {
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
    USUARIO_SESION({ state }, usuario) {
      state.usuario = usuario
    }
  },
  actions: {
    actualizarUsuarioSesion({ state, commit }, usuario) {
      if (state.usuario) {
        commit('USUARIO_SESION', usuario)
      }
    }
  },
  modules: {
  }
})
