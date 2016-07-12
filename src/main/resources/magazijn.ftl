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
    <h2>Welkom op pagina van locatie: <strong>${magazijn.locatie}</strong></h2>
</div>

<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h3>Acties</h3>

            <p><a href="#" data-toggle="modal" data-target="#p-modal">Voeg een product toe</a></p>
            <p class="text-muted"><a href="/magazijn/verwijderMagazijn?id=${magazijn.id}">Verwijder magazijn</a></p>
            <#if isSuperuser??>
                <p><a href="#" data-toggle="modal" data-target="#m-modal">Voeg een medewerker toe</a></p>
            </#if>
            <br>
            <p class="text-muted"><a href="/logout"><strong>Logout</strong></a></p>

        </div>
<#if isSuperuser??>

        <div class="col-sm-4">
            <h3>Alle medewerkers</h3>
            <#if medewerkers??>
                <#list medewerkers as medewerker>
                    <p><a href="/home/medewerker?id=${medewerker.id}">${medewerker.naam}</a></p>
                </#list>
            </#if>
        </div>
</#if>
        <div class="col-sm-4">
            <h3>Producten van ${magazijn.locatie}</h3>
        <#if producten??>
            <#list producten as product>
                <p><a href="/home/product?id=${product.id}">${product.naam} (${product.aantal})</a></p>
            </#list>
        </#if>
        </div>
    </div>
</div>

<div class="modal fade" id="m-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     style="display: none;">
    <div class="modal-dialog">
        <div class="input-group">
            <h1 style="color: white;">Hier kan je medewerker toevoegen</h1><br>
            <form action="/medewerkerToevoegen?id=${magazijn.id}" method="post">
                <input type="text" class="form-control"  name="naam" placeholder="Naam"><br>
                <input type="text" class="form-control"  name="username" placeholder="Username"><br>
                <input type="password" class="form-control"  name="password" placeholder="Password"><br>
                <input type="submit" class="form-control" value="Voeg toe">
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="p-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     style="display: none;">
    <div class="modal-dialog">
        <div class="input-group">
            <h1 style="color: white;">Hier kan je product toevoegen</h1><br>
            <form action="/productToevoegen?id=${magazijn.id}" method="post">
                <input type="text" class="form-control"  name="naam" placeholder="Naam"><br>
                <input type="text" class="form-control"  name="prijs" placeholder="Prijs"><br>
                <input type="text" class="form-control"  name="omschrijving" placeholder="Omschrijving"><br>
                <input type="text" class="form-control"  name="aantal" placeholder="Aantal"><br>
                <input type="submit" class="form-control" value="Voeg toe">
            </form>
        </div>
    </div>
</div>

</body>
</html>

