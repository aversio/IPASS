<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bike2Go Voorraadsysteem</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>

<div class="jumbotron text-center">
    <h2>Welkom op pagina van product: <strong>${product.naam}</strong></h2>
</div>

<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h3>Acties</h3>
            <p><a href="/home/product/verwijderProduct?id=${product.id}">Product verwijderen</a></p>
            <p><a href="#" data-toggle="modal" data-target="#pw-modal">Product wijzigen</a></p>
            <p class="text-muted"><a href="/logout"><strong>Logout</strong></a></p>

        </div>
        <div class="col-sm-4">
            <h3>Product: ${product.naam}</h3>
            <table class="table" style="width:100% !important;">
                <thead>
                <tr>
                    <th>Naam</th>
                    <th>Prijs</th>
                    <th>Omschrijving</th>
                    <th>Aantal</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${product.naam}</td>
                    <td>${product.prijs}</td>
                    <td>${product.omschrijving}</td>
                    <td>${product.aantal}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

</div>

<div class="modal fade" id="pw-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     style="display: none;">
    <div class="modal-dialog">
        <div class="input-group">
            <h1 style="color: white;">Wijzig product ${product.naam}</h1><br>
            <form action="/home/product/wijzigProduct?id=${product.id}" method="post">
                <input type="text" class="form-control"  name="naam" value="${product.naam}"><br>
                <input type="text" class="form-control"  name="prijs" value="${product.prijs}"><br>
                <input type="text" class="form-control"  name="omschrijving" value="${product.omschrijving}"><br>
                <input type="text" class="form-control"  name="aantal" value="${product.aantal}"><br>
                <input type="submit" class="form-control" value="Wijzig">
            </form>
        </div>
    </div>
</div>

</body>
</html>

