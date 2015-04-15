<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>À propos de Toulouse-Musées</title>
</head>
<body>
<h1>Toulouse Musees</h1>

<p>Toulouse Museesis a project for University Toulouse III Paul Sabatier in DCLL subject.</p>
<iframe src="https://ghbtns.com/github-btn.html?user=aroquemaurel&repo=Toulouse-Musees&type=star&count=true" frameborder="0" scrolling="0" width="170px" height="20px"></iframe>
<iframe src="https://ghbtns.com/github-btn.html?user=aroquemaurel&repo=Toulouse-Musees&type=watch&count=true&v=2" frameborder="0" scrolling="0" width="170px" height="20px"></iframe>
<iframe src="https://ghbtns.com/github-btn.html?user=aroquemaurel&repo=Toulouse-Musees&type=fork&count=true" frameborder="0" scrolling="0" width="170px" height="20px"></iframe>
<h2>Members</h2>
<p>
    This project is developped by :
    <ul>
        <li>Florent Berbie (<a href="https://github.com/KraTuX31">@KraTuX</a>)</li>
        <li>Antoine de Roquemaurel (<a href="https://github.com/aroquemaurel">@aroquemaurel</a>)</li>
    </ul>
<h2>The project</h2>
<p>Toulouse wants to help tourists in specific period. We develop an application for registration of users who would like to visit museums.</p>

<p>Utils museums data is in [Musee.csv](documents/Musee.csv) file.<br/>
This file was created by [Toulouse Museum](https://data.toulouse-metropole.fr/)</p>

<h2>Environment</h2>

<table class="table table-condensed table-striped">
    <tr>
    <th>Environment</th>
    <th>Version</th>
    </tr>
    <tr>
        <td>Development Framework</td>
        <td> Grails 2.4.2</td>
    </tr>
    <tr>
        <td>IDE</td>
        <td> IntelliJ version 13.1.1 or higher</td>
    </tr>
    <tr>
        <td>Test framework</td>
        <td> Spock (Grails version)</td>
    </tr>
    <tr>
        <td>SGBDR</td>
        <td>H2 (Grails version)</td>
    </tr><tr>
        <td>Test coverage</td>
        <td>Plugin coverage 2.0.3-3 (Cobertura)</td>
    </tr><tr>
        <td>Version Controle System</td>
        <td>Git</td>
    </tr><tr>
        <td>Shared Git repository</td>
        <td>Github</td>
    </tr>
</table>




<h1>Application Status</h1>
			<ul>
				<li>App version: <g:meta name="app.version"/></li>
				<li>Grails version: <g:meta name="app.grails.version"/></li>
				<li>Groovy version: ${GroovySystem.getVersion()}</li>
				<li>JVM version: ${System.getProperty('java.version')}</li>
				<li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>
				<li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
				<li>Domains: ${grailsApplication.domainClasses.size()}</li>
				<li>Services: ${grailsApplication.serviceClasses.size()}</li>
				<li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
			</ul>
			<h1>Installed Plugins</h1>
			<ul>
				<g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
    <li>${plugin.name} - ${plugin.version}</li>
</g:each>
			</ul>
</body>
</html>
