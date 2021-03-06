Serval Mesh README
==================

Serval Mesh is an app for [Android 2.2 “Froyo”][] and above.  It provides free,
secure phone-to-phone voice calling, SMS and file sharing over WiFi, without
the need for a SIM card or a commercial mobile telephone carrier.  In other
words, it lets your Android phone communicate with other Android phones running
Serval Mesh within WiFi range.

The latest release of Serval Mesh is available for download on [Google Play][].

Serval Mesh is [free software][] produced by the [Serval Project][] and many
[contributors][].  The Java/XML source code of Serval Mesh is licensed to the
public under the [GNU General Public License version 3][GPL3].  The
[serval-dna][] component of Serval Mesh is licensed to the public under the
[GNU General Public License version 2][GPL2].  All source code is freely
available from the Serval Project's [batphone][] and [serval-dna][] Git
repositories on [GitHub][].

Warnings
--------

Serval Mesh version 0.90 is **EXPERIMENTAL SOFTWARE**.  It has not yet reached
version 1.0, and is intended for pre-production, demonstration purposes only.
It may not work as advertised, it may lose or alter messages and files that it
carries, it may consume a lot of space, speed and battery, and it may crash
unexpectedly.

This version of Serval Mesh requires [root permission][] on your Android device
in order to put its WiFi into [AdHoc mode][].  It can function without root
permission, but will be limited to using an existing WiFi home network or hot
spot, and may not work altogether.

Serval Mesh will interfere with the normal operation of your Android device.
In particular, while activated, it will take control of your device's WiFi
network and use it to contact other Serval Mesh devices in the vicinity.  This
will cut it off from any existing WiFi network.

Serval Mesh telephony is a “best effort” service, primarily intended for when
conventional telephony is not possible or cost effective, and MUST NOT BE
RELIED UPON for emergencies in place of carrier-grade communications systems.
The Serval Project cannot be held responsible for any performance or
non-performance of the technologies that they provide in good will, and if you
use these technologies you must agree to indemnify the Serval Project from any
such claims.

The Serval Mesh software copies all files shared using the Rhizome file
dissemination service to other phones and devices running the Serval Mesh
software, regardless of size, content or intended recipient.  The Serval
Project cannot be held responsible for the legality or propriety of any files
received via Rhizome, nor for any loss, damage or offence caused by the
transmission or receipt of any content via Rhizome.

Documentation
-------------

* [CURRENT-RELEASE.md](./CURRENT-RELEASE.md) (alternative link
  [CURRENT-RELEASE.md](./blob/release/CURRENT-RELEASE.md)) Release notes for
  the current release.

* [INSTALL.md](./INSTALL.md) (alternative link
  [INSTALL.md](./blob/release/INSTALL.md)) Instructions for building the
  Android APK from the source code and installing manually.

* [doc](./doc/) (alternative link [doc](./tree/release/doc/)) Technical
  documentation, including past release notes.

* [Serval Wiki][] contains helpful information for developers and users,

* [GitHub Issues][] Tracking of bug reports and tasks.  Please see the wiki
  pages on reporting bugs and managing issues before adding any new issues.


[Android 2.2 “Froyo”]: http://developer.android.com/about/versions/android-2.2-highlights.html
[Serval Project]: http://www.servalproject.org/
[contributors]: ./CONTRIBUTORS.md
[Google Play]: https://play.google.com/store/apps/details?id=org.servalproject
[GPL3]: http://gplv3.fsf.org/
[GPL2]: http://www.gnu.org/licenses/gpl-2.0.html
[batphone]: https://github.com/servalproject/batphone
[serval-dna]: https://github.com/servalproject/serval-dna
[GitHub]: https://github.com/servalproject
[free software]: http://www.gnu.org/philosophy/free-sw.html
[root permission]: http://en.wikipedia.org/wiki/Android_rooting
[AdHoc mode]: http://compnetworking.about.com/cs/wirelessfaqs/f/adhocwireless.htm
[Serval Wiki]: http://developer.servalproject.org/dokuwiki
[GitHub Issues]: https://github.com/servalproject/batphone/issues
