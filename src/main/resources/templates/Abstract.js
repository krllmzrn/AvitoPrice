$(document).ready(function() {
    $("#select").change(function() {
        var selectedOption = $(this).children("option:selected");
        var optionValue = selectedOption.val();
        var optionText = selectedOption.text();

        $.ajax({
            type: "POST",
            url: "/showSelectedTable",
            data: {
                optionValue: optionValue,
                optionText: optionText
            },
            success: function(data) {
                return optionValue
            }
        });
    });
});