package com.example;

import no.fint.Link;
import no.fint.model.felles.CollectionLinks;
import no.fint.model.felles.PersonLinks;
import no.fint.model.felles.PersonResource;
import no.fint.model.felles.PersonResources;
import no.fint.model.felles.kodeverk.iso.Kjonn;
import no.fint.model.felles.kodeverk.iso.Landkode;
import no.fint.model.felles.kompleksedatatyper.Adresse;
import no.fint.model.felles.kompleksedatatyper.AdresseLinks;
import no.fint.model.felles.kompleksedatatyper.AdresseResource;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    @GetMapping("/person")
    public PersonResource getPerson() {

        Link<Landkode> landkodeLink = new Link<>();
        landkodeLink.setVerdi("http://localhost");
        List<Link<Landkode>> landkodeLinks = new ArrayList<>();
        landkodeLinks.add(landkodeLink);

        AdresseLinks adresseLinks = new AdresseLinks();
        adresseLinks.setLand(landkodeLinks);

        AdresseResource adresseResource = new AdresseResource();
        adresseResource.setLinks(adresseLinks);

        Identifikator identifikator = new Identifikator();
        identifikator.setIdentifikatorverdi("1234567890");

        Link<Kjonn> kjonnLink = new Link<>();
        kjonnLink.setVerdi("http://localhost");
        List<Link<Kjonn>> kjonnLinks = new ArrayList<>();
        kjonnLinks.add(kjonnLink);

        PersonLinks personLinks = new PersonLinks();
        personLinks.setKjonn(kjonnLinks);

        PersonResource personResource = new PersonResource();
        personResource.setFodselsnummer(identifikator);
        personResource.setBostedsadresse(adresseResource);
        personResource.setPostadresse(adresseResource);


        List<String> adresseLinje = new ArrayList<>();
        adresseLinje.add("test123");
        Adresse adresse = new Adresse();
        adresse.setPostnummer("1234");
        adresse.setAdresselinje(adresseLinje);
        //personResource.setBostedsadresse(adresse);

        personResource.setLinks(personLinks);

        return personResource;
    }

    @GetMapping("/persons")
    public PersonResources getPersons() {
        PersonResource person = getPerson();

        Link<String> selfLink = new Link<>();
        selfLink.setVerdi("http://localhost:8080");

        List<Link<String>> links = new ArrayList<>();
        links.add(selfLink);

        CollectionLinks collectionLinks = new CollectionLinks();
        collectionLinks.setSelf(links);

        PersonResources personResources = new PersonResources();
        personResources.addResource(person);
        personResources.setLinks(collectionLinks);

        return personResources;
    }

}
