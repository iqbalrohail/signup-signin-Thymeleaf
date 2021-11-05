
const search=()=>{

    let query=$("#search_input").val();


    if (query=='')
    {
        $(".search-result").hide();



    }else{

        console.log(query);

        let url =`http://localhost:8080/search/${query}`;
        fetch(url).then((response)=>{
            return response.json();
        }).then((data)=>{
            console.log(data);

            let text = `<div class ='list-group'>`;

            data.forEach((recipe)=>{

                text+=`<a href='/recipe-detail/${recipe.id}' class='list-group-item list-group-item-action'>
${recipe.description}
</a>`

            });


            text+=`</div>`;

            $(".search-result").html(text);
            $(".search-result").show();

        });

    }
}