#!/usr/bin/env bash
set -euo pipefail

project_dir="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
cd "$project_dir"

if ! command -v tigervncserver >/dev/null || ! command -v websockify >/dev/null; then
    printf '%s\n' "TigerVNC and websockify must be installed to run the GUI in Codespaces." >&2
    exit 1
fi

if ! timeout 2 bash -c 'exec 3<>/dev/tcp/127.0.0.1/5901' 2>/dev/null; then
    tigervncserver -cleanstale
    tigervncserver :1 -localhost yes -geometry 1280x800 -depth 24

    for _ in {1..10}; do
        if timeout 2 bash -c 'exec 3<>/dev/tcp/127.0.0.1/5901' 2>/dev/null; then
            break
        fi
        sleep 1
    done

    if ! timeout 2 bash -c 'exec 3<>/dev/tcp/127.0.0.1/5901' 2>/dev/null; then
        printf '%s\n' "TigerVNC did not start on port 5901. Check ~/.vnc/*.log for details." >&2
        exit 1
    fi
fi

if ! curl --fail --silent http://127.0.0.1:6080/vnc.html >/dev/null; then
    nohup websockify --web=/usr/share/novnc/ 6080 localhost:5901 \
        >"$HOME/.vnc/websockify.log" 2>&1 </dev/null &

    for _ in {1..10}; do
        if curl --fail --silent http://127.0.0.1:6080/vnc.html >/dev/null; then
            break
        fi
        sleep 1
    done

    if ! curl --fail --silent http://127.0.0.1:6080/vnc.html >/dev/null; then
        printf '%s\n' "noVNC did not start. See ~/.vnc/websockify.log for details." >&2
        exit 1
    fi
fi

export DISPLAY=:1
exec mvn javafx:run
