(function ($, document, ns) {
    $(document).on("dialog-ready", function() {
    	// In dialog ready lets request the JSON and store it
        let citiesJSON;
		$.ajax({
            url: "/content/dam/ugams/cities.json", //Update the path
            async: false,
            success: function (data) {
                citiesJSON = data;
            }
        });

        const setCitiesOptions = function () {
            let citiesField = $(".cq-dialog").find("#cities")[0];
            let countryValue = $(".cq-dialog").find("#country")[0].selectedItem.value;
            let options = citiesJSON[countryValue];
            let optionItems = citiesField.items;
            optionItems.clear();
            for (var i = 0; i < options.length; i++) {
                let obj = new Object();
                let cnt = new Object();
                obj["value"] = options[i].value;
                cnt["textContent"] = options[i].text;
                obj["content"] = cnt;
                optionItems.add(obj);
            }
        };


       $(".cq-dialog").find("#country").on("change", setCitiesOptions);
        setCitiesOptions();
    });
})(Granite.$, document, Granite.author);