import by.dev.madhead.playgrounds.sprink5.skript.model.BreakingBadCharacter

"""
<html>
    <head>
        <title>Kotlin String template</title>
    </head>
    <body>
        <ul>
            <li>
                <h3>Jessie Pinkman</h3>
                <dl>
                    <dt>Name:</dt>
                    <dd>${(bindings["jessie"] as BreakingBadCharacter).firstName}</dd>
                    <dt>Last name:</dt>
                    <dd>${(bindings["jessie"] as BreakingBadCharacter).lastName}</dd>
                    <dt>Status:</dt>
                    <dd>${(bindings["jessie"] as BreakingBadCharacter).status}</dd>
                </dl>
            </li>
            <li>
                <h3>Walter White</h3>
                <dl>
                    <dt>Name:</dt>
                    <dd>${(bindings["heisenberg"] as BreakingBadCharacter).firstName}</dd>
                    <dt>Last name:</dt>
                    <dd>${(bindings["heisenberg"] as BreakingBadCharacter).lastName}</dd>
                    <dt>Status:</dt>
                    <dd>${(bindings["heisenberg"] as BreakingBadCharacter).status}</dd>
                </dl>
            </li>
        </ul>
    </body>
</html>
"""
