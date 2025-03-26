var contextPath = window.location.pathname.split("/")[1]; // Lấy "SpringMVCApp"

function deleteProduct(path, id) {
        console.log(contextPath)
        fetch(`/${contextPath}/${path}/${id}/`, {
            method: "DELETE"
        }).then(res => {
            if (res.status === 204) {
                location.reload()
            } else {
                alert("Something wrong")
            }
        })

}