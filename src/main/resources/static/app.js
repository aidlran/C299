/**
 * Get a resource from the REST API.
 * @param {string} url
 * @returns {*} A JSON object.
 */
const get = url => fetch("/api" + url).then(response => response.json());

/**
 * More advanced element creation function for better code flow.
 * @param {string} [type] The type of element, i.e. 'div'.
 * @param {HTMLElement|function(HTMLElement)} [properties] Either an object containing properties to overwrite on the element or the callback if not using this functionality.
 * @param {function(HTMLElement)} [callback] A callback with the element passed in.
 * @returns {HTMLElement} The created element.
 */
const createElement = (type = 'div', properties, callback) => {
	const element = document.createElement(type);
	switch (typeof properties) {
		case 'object':
			Object.assign(element, properties);
			break;
		case 'function':
			properties(element);
			break;
	}
	if (typeof callback === 'function') callback(element);
	return element;
};

/**
 * @typedef View
 * @property {function} render Renders the view.
 */

/**
 * API for views.
 */
const VIEW = (() => {

	const CONTENT_ROOT = document.getElementById('content-root');

	return {

		/**
		 * Changes the active view.
		 * @param {View} view A View object.
		 */
		set(view) {

			while (CONTENT_ROOT.firstChild)
				CONTENT_ROOT.removeChild(CONTENT_ROOT.lastChild);

			view.render();
		},

		/**
		 * View for the most recent superhero sightings.
		 * @type View
		 */
		RECENT_SIGHTINGS: {
			render: () => get('/sighting/recent')
				.then(data => CONTENT_ROOT.append(
					createElement('h1', {innerText: "Recent Sightings"}),
					data.length == 0
						? createElement('p', {innerText: "No recent Sightings."})
						: createElement('table', table => table.append(
							createElement('thead', thead => thead.appendChild(
								createElement('tr', row => ["Super", "Location", "Date", "Notes"]
									.forEach(innerText => row.appendChild(createElement('th', {innerText})))
								)
							)),
							createElement('tbody', tbody => data.forEach(sighting => tbody.appendChild(
								createElement('tr', row => row.append(
									createElement('td', cell => get('/character/' + sighting['characterId'])
										.then(character => cell.innerText = character['name'] ?? "")),
									createElement('td', cell => get('/location/' + sighting['locationId'])
										.then(location => cell.innerText = location['name'] ?? "")),
									createElement('td', {innerText: sighting['timestamp']}),
									createElement('td', {innerText: sighting['description']})
								))
							)))
						))
				))
		}
	};
})();

/**
 * Adds a button to the navigation bar.
 * @param {string} innerText The button's text label.
 * @param {View} view The View to activate when the button is clicked.
 */
const createNavButton = (() => {

	const NAV = document.getElementById('navigation');

	return (innerText, view) => NAV.appendChild(createElement('span', {innerText}, button =>
		button.addEventListener('click', event => {
			event.stopPropagation();
			VIEW.set(view);
		}
	)));
})();

createNavButton("Recent Sightings", VIEW.RECENT_SIGHTINGS);

VIEW.set(VIEW.RECENT_SIGHTINGS);
