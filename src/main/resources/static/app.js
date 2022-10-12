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
			render() {

			}
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
