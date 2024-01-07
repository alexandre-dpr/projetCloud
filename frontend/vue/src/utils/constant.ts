import {ref} from "vue";

export function getApiServer() {
  const API_SERVER = ref(process.env.VUE_APP_API_URL)
  if (API_SERVER.value) {
    console.log("Inchangé", API_SERVER.value)

    if (API_SERVER.value.includes("/undefined")) {
      API_SERVER.value = API_SERVER.value.replace("/undefined", "")
      console.log("Replaced", API_SERVER.value)
      return API_SERVER.value

    } else {
      return API_SERVER.value
    }
  } else {
    console.log(API_SERVER.value)
  }
}
