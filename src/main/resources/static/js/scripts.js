var dataTableInstance;

$(document).ready(function () {
    $("#getDataBtn").click(function () {
        $("#tableBody").empty();
        $("#tableHeader").empty();
        $("#tableHeader").css("color", "black");

        var selectedDate = $("#date").val();
        var selectedExchangeName = $("#exchangeName").val();

        $.ajax({
            type: "GET",
            url: "/getdata",
            data: {
                date: selectedDate,
                exchangeName: selectedExchangeName
            },
            success: function (data) {
                if (data.length > 0) {
                    var dynamicHeader = selectedDate + " " + selectedExchangeName;
                    $("#tableHeader").text(dynamicHeader);

                    if (dataTableInstance) {
                        dataTableInstance.destroy();
                    }

                    updateTable(data);

                    dataTableInstance = $('#sortableTable').DataTable({
                        "columnDefs": [
                            {"width": "20%", "targets": 0},
                            {"width": "40%", "targets": 1},
                            {"width": "20%", "targets": 2},
                            {"width": "20%", "targets": 3}
                        ],
                        dom: 'lBfrtip', // Show length menu, buttons, filter, and processing
                        buttons: [
                            {
                                extend: 'csv',
                                text: 'Download CSV',
                                filename: dynamicHeader
                            }
                        ],
                        lengthMenu: [[10, 25, 50, -1], [10, 25, 50, "All"]]
                    });
                } else {
                    $("#tableHeader").text("No data found for: " + selectedDate + " " + selectedExchangeName);
                    $("#tableHeader").css("color", "red");
                }
            },
            error: function (error) {
                console.error("Error fetching data:", error);
            }
        });
    });

    function updateTable(data) {
        $("#tableBody").empty();

        for (var i = 0; i < data.length; i++) {
            var row = "<tr><td>" + data[i].name + "</td><td>" + data[i].price + "</td><td>" + data[i].quantity + "</td><td>" + data[i].dailyChangeRate + "</td></tr>";
            $("#tableBody").append(row);
        }
    }
});
