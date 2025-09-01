// Mobile nav
document.addEventListener('DOMContentLoaded', () => {
  const btn = document.getElementById('menuBtn');
  const nav = document.getElementById('mobileNav');
  if (btn && nav) btn.addEventListener('click', () => nav.classList.toggle('hidden'));

  // Dark mode
  const toggle = document.getElementById('darkToggle');
  const root = document.documentElement;
  const pref = window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches;
  const saved = localStorage.getItem('theme');
  if ((saved === 'dark') || (!saved && pref)) root.classList.add('dark');
  if (toggle) {
    toggle.addEventListener('click', () => {
      root.classList.toggle('dark');
      localStorage.setItem('theme', root.classList.contains('dark') ? 'dark' : 'light');
    });
  }
});
