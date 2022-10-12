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
			render: () => fetch('/api/sighting/recent')
				.then(response => response.json())
				.then(data => CONTENT_ROOT.append(
					(heading => {
						heading.innerText = "Recent Sightings";
						return heading;
					})(document.createElement('h1')),
					data.length == 0
						? (message => {
							message.innerText = "No recent sightings.";
							return message;
						})(document.createElement('p'))
						: (table => {
							table.append(
								(thead => thead.appendChild((row => {
									["Super", "Location", "Time", "Notes"]
										.forEach(label => row.appendChild((cell => {
											cell.innerText = label;
											return cell;
										})(document.createElement('th')))
									);
									return row;
								})(document.createElement('tr'))))(document.createElement('thead')),
								(tbody => {
									data.forEach(sighting =>
										tbody.appendChild((row => {
											[
												// TODO: fetch character and location info
												sighting['characterId'],
												sighting['locationId'],
												sighting['timestamp'],
												sighting['description']
											].forEach(field => row.appendChild((cell => {
												cell.innerText = field;
												return cell;
											})(document.createElement('td'))))
											return row;
										})(document.createElement('tr')))
									)
									return tbody;
								})(document.createElement('tbody'))
							);
							return table;
						})(document.createElement('table'))
				))
		}
	};
})();

/**
 * Adds a button to the navigation bar.
 * @param {string} label The button's text label.
 * @param {View} view The View to activate when the button is clicked.
 */
const createNavButton = (() => {

	const NAV = document.getElementById('navigation');

	return (label, view) => {
		const button = NAV.appendChild(document.createElement('span'));
		button.innerText = label;
		button.addEventListener('click', event => {
			event.stopPropagation();
			VIEW.set(view);
		});
	};
})();

createNavButton("Recent Sightings", VIEW.RECENT_SIGHTINGS);

VIEW.set(VIEW.RECENT_SIGHTINGS);
